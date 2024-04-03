import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные о пользователе (Фамилия Имя Отчество дата_рождения номер_телефона пол): ");
        String userData = scanner.nextLine();

        String[] userDataArray = userData.split(" ");

        if (userDataArray.length != 6) {
            System.out.println("Ошибка: Введено неверное количество данных. Требуется 6 значений.");
            return;
        }

        String surname = userDataArray[0];
        String firstName = userDataArray[1];
        String lastName = userDataArray[2];
        String birthDateString = userDataArray[3];
        String phoneNumber = userDataArray[4];
        String gender = userDataArray[5];

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date birthDate;
        try {
            birthDate = dateFormat.parse(birthDateString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(birthDate);
            int year = cal.get(Calendar.YEAR);
            if (year < 1900 || year > Calendar.getInstance().get(Calendar.YEAR)) {
                System.out.println("Ошибка: Неправильный год рождения. Введите корректную дату рождения.");
                return;
            }
        } catch (ParseException e) {
            System.out.println("Ошибка: Неверный формат даты. Используйте формат dd.mm.yyyy.");
            return;
        }

        if (!gender.equals("f") && !gender.equals("m")) {
            System.out.println("Ошибка: Пол должен быть указан как 'f' (женский) или 'm' (мужской).");
            return;
        }

        if (!phoneNumber.matches("\\d+")) {
            System.out.println("Ошибка: Номер телефона должен состоять только из цифр.");
            return;
        }

        try {
            File file = new File(surname + ".txt");
            FileWriter writer = new FileWriter(file, true);

            SimpleDateFormat newDateFormat = new SimpleDateFormat("dd.MM.yyyy");

            writer.write(surname + " " + firstName + " " + lastName + " "
                    + newDateFormat.format(birthDate) + " " + phoneNumber + " " + gender + "\n");
            writer.close();

            System.out.println("Данные успешно записаны в файл " + surname + ".txt");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл:");
            e.printStackTrace();
        }
    }
}