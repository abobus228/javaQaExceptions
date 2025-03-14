package ru.netology;

public class ShopRepository {
    private Product[] products = new Product[0];

    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public void add(Product product) {

        // Проверка, существует ли в массиве элемент с таким же ID
        Product examID = findById(product.getId());
        if (examID != null) {
            throw new AlreadyExistsException("Element with id: " + product.getId() + " already exists");
        }

        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }

    public void remove(int id) {
        Product[] tmp = new Product[products.length - 1];

        // Проверка, есть ли элемент с данным ID в массиве
        Product examID = findById(id);
        if (examID == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }

        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    // Поиск элемента в массиве по ID
    public Product findById(int id) {
        for (Product product : products) {
            if (product.id == id) {
                return product;
            }
        }
        return null;
    }
}