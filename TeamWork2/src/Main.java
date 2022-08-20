import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] products = {"Гречка", "Молоко", "Сыр", "Хлеб"};
        String[] productsAction = {"Молоко", "Сыр"};
        double[] prices = {3.0, 140, 23.5, 50};
        int[] selected = new int[products.length];
        System.out.println("Список возможных товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println(i + 1 + " " + products[i] + "  " + prices[i] + " руб/шт");
        }
        System.out.println("Внимание! На следующие товары - акция, 3 по цене 2:");
        for (int i = 0; i < productsAction.length; i++) {
            System.out.println(productsAction[i]);
        }

        while (true) {
            System.out.println("Выберите номер товара и количество или введите `end`");
            String input = scanner.nextLine();

            int currentProduct;
            int currentQuan;
            String parts[] = input.split(" ");
            if (parts.length == 2) {
                try {
                    currentProduct = Integer.parseInt(parts[0]);
                    currentQuan = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Одно из введенных значений, либо оба - не являются числом!");
                    continue;
                }

                if (currentProduct < 1 || currentProduct > products.length) {
                    System.out.println("Товара под номером " + currentProduct + " не существует!");
                    continue;
                }

                if (Integer.parseInt(parts[1]) < 0) {
                    System.out.println("Нельзя выбрать такое количество товара! ");
                    continue;
                }

                selected[currentProduct - 1] = selected[currentProduct - 1] + currentQuan;
            } else if ("end".equals(input)) {
                System.out.println("Ваша корзина: ");
                double sum = 0;
                for (int q = 0; q < selected.length; q++) {
                    if (selected[q] > 0) {

                        for (int pa = 0; pa < productsAction.length; pa++) {
                            if (productsAction[pa].equals(products[q]) && selected[q] > 2) {
                                System.out.println(" " + products[q] + " " + (selected[q] / 3) * 3 +
                                        " шт. по специальной цене " + (selected[q] / 3) * 2 * prices[q] +
                                        " в сумме.");
                                sum = sum + (selected[q] / 3) * 2 * prices[q];
                                if (selected[q] % 3 != 0) {
                                    System.out.println(" " + products[q] + " " + selected[q] % 3 +
                                            " шт. " + selected[q] % 3 * prices[q] +
                                            " в сумме.");
                                    sum = sum + selected[q] % 3 * prices[q];
                                }
                                break;
                            }
                            if (pa == productsAction.length - 1) {
                                System.out.println(" " + products[q] + " " + selected[q] +
                                        " шт. " + prices[q] + " руб/шт " + selected[q] * prices[q] + " в сумме.");
                                sum = sum + selected[q] * prices[q];
                            }


                        }

                    }

                }
                System.out.println("Итого: " + sum);
                break;
            } else {
                System.out.println("Введено неверное количество чисел! ");
                continue;
            }
        }
    }
}