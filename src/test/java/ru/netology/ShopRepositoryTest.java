package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {

    Product product1 = new Product(1, "Хлеб", 55);
    Product product2 = new Product(2, "Батон", 67);
    Product product3 = new Product(3, "Булка с маком", 46);
    Product product4 = new Product(4, "Сосиска в тесте", 82);
    Product product5 = new Product(5, "Булочка с изюмом", 75);

    ShopRepository repository = new ShopRepository();

    @BeforeEach
    public void start() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);
        repository.add(product4);
    }

    @Test  // Удаление элемента из массива с существующим ID
    public void shouldRemoveProductById() {

        repository.remove(2);

        Product[] expected = {product1, product3, product4};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test  // Попытка удаления элемента из массива с несуществующим ID
    public void shouldReturnExceptionAtRemoveProductById() {

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.remove(6);
        });

    }

    @Test  // Добавление элемента в массив с уникальным ID
    public void shouldAddProductByNewId() {

        repository.add(product5);

        Product[] expected = {product1, product2, product3, product4, product5};
        Product[] actual = repository.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test  // Попытка добавления элемента в массив с уже существующим ID
    public void shouldReturnExceptionAtAddProductById() {

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product3);  // Повторное добавление одного продукта
        });

    }
}
