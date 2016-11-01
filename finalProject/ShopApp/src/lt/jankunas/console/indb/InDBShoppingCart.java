package lt.jankunas.console.indb;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lt.jankunas.shop.*;

public class InDBShoppingCart implements ShoppingCart {

    private Connection conn;
    private InDBStatementHandling statementHandling;
    private PreparedStatement statement;

    public InDBShoppingCart(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Product product) {
        try {
            statementHandling = new InDBStatementHandling(conn);
            statement = statementHandling.prepareAddToCartCommand(product);
            statement.executeUpdate();
            System.out.format("%d %s for %.2f each has(have) been successfully added to the shopping cart",
                    product.getQuantity(), product.getName(), product.getPrice());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(String product, int quantity) {
        try {
            statementHandling = new InDBStatementHandling(conn);
            statement = statementHandling.prepareUpdateRemoveFromCartCommand(product, quantity);
            if (statement.executeUpdate() > 0) {
                System.out.format("%d %s has(have) been removed from inventory", quantity, product);
                statement = statementHandling.prepareDeleteRemoveFromCartCommand();
                statement.executeUpdate();
            } else
                System.out.format("%s does not exist in inventory", product);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Product> getShoppingCartList() {
        List<Product> shoppingCart = new ArrayList<Product>();
        try (PreparedStatement statement = conn.prepareStatement("SELECT name, quantity, price FROM shopping_cart");) {
            try (ResultSet result = statement.executeQuery();) {
                while (result.next()) {
                    shoppingCart.add(
                            new Product(result.getString("name"), result.getInt("quantity"), result.getFloat("price")));
                }
            }
            return shoppingCart;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.<Product>emptyList();
    }

    public float getSum() {
        float total = 0;
        try (PreparedStatement statement = conn.prepareStatement("SELECT price,quantity FROM shopping_cart");) {
            try (ResultSet result = statement.executeQuery();) {
                while (result.next()) {
                    total = computeSum(total, computeProduct(result.getFloat("price"), result.getInt("quantity")));
                }
            }
            return total;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public void delete() {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM shopping_cart");) {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getCartProductByName(String product) {
        try (PreparedStatement statement = conn
                .prepareStatement("SELECT name, quantity, price FROM shopping_cart WHERE name = ?");) {
            statement.setString(1, product);
            try (ResultSet result = statement.executeQuery();) {
                if (result.next())
                    return new Product(result.getString("name"), result.getInt("quantity"), result.getFloat("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void show(User user) {
        try {
            statementHandling = new InDBStatementHandling(conn);
            statement = statementHandling.prepareCartShowCommand();
            try (ResultSet result = statement.executeQuery();) {
                System.out.format("Current items in shopping cart%n-------------------------------%n");
                while (result.next()) {
                    String productName = result.getString("name");
                    int productQuantity = result.getInt("quantity");
                    System.out.format("Name: %-8s|Quantity: %-4d%n-------------------------------%n", productName,
                            productQuantity);
                }
                System.out.format("Total payable amount: %.2f%n-------------------------------%n", getSum());
                statement = statementHandling.prepareCashShowCommand(user);
            }
            try (ResultSet result = statement.executeQuery();) {
                if (result.next()) {
                    Float cashLeft = result.getFloat("cash");
                    System.out.format("Cash left: %.2f", cashLeft);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private float computeProduct(Float price, int quantity) {
        String productPrice = Float.toString(price);
        String productQuantity = Integer.toString(quantity);
        BigDecimal toComputePrice = new BigDecimal(productPrice);
        BigDecimal toComputeQuantity = new BigDecimal(productQuantity);
        BigDecimal product = toComputePrice.multiply(toComputeQuantity);

        return product.floatValue();
    }

    private float computeSum(Float total, float addition) {
        String totalAmount = Float.toString(total);
        String additionAmount = Float.toString(addition);
        BigDecimal toComputeTotal = new BigDecimal(totalAmount);
        BigDecimal toComputeAddition = new BigDecimal(additionAmount);
        BigDecimal sum = toComputeTotal.add(toComputeAddition);

        return sum.floatValue();
    }
}