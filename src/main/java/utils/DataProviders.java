package utils;

import model.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> newContact() {

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"TestName", "TestSurname", "+797812345", "email@mail.co", "BerlinAdress", "Description"});
        list.add(new Object[]{"OnemoreName", "OnemoreSurname", "+797324378", "mail@gmail.co", "SityAdress", "About"});
        list.add(new Object[]{"MoreName", "MoreSurname", "+791059945", "po4ta@mail.co", "Simfero", "What"});

        return list.iterator();

    }

    @DataProvider
    public Iterator<Object[]> newContactWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Contacts.csv")));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split(",");

            list.add(new Object[]{new Contact().setName(split[0])
                    .setSurname(split[1])
                    .setTelephone(split[2])
                    .setEmail(split[3])
                    .setAddress(split[4])
                    .setDescription(split[5])});
            line = reader.readLine();
        }

        return list.iterator();

    }
}
