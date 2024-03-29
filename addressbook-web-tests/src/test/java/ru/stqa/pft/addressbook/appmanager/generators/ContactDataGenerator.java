package ru.stqa.pft.addressbook.appmanager.generators;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.appmanager.model.ContactData;
import ru.stqa.pft.addressbook.appmanager.model.Groups;
import ru.stqa.pft.addressbook.appmanager.tests.TestBase;
import ru.stqa.pft.addressbook.appmanager.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator extends TestBase {

    @Parameter(names = "-c", description = "Contact count")
    public  int count;
    @Parameter(names =  "-f", description = "Target file")
    public  String file;
    @Parameter(names =  "-d", description = "Data format")
    public  String format;




    public static void main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> Contacts  = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(Contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(Contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(Contacts, new File(file));
        } else {
            System.out.println ("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> Contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(Contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> Contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(Contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(xml);
        }

    }

    private void saveAsCsv(List<ContactData> Contacts, File file) throws IOException {
        System.out.println(new File(".").getAbsoluteFile());
        try (Writer writer = new FileWriter(file))  {
            for (ContactData contact : Contacts) {
                writer.write((String.format("%s;%s;%s;%s;\n", contact.getName(), contact.getSurname(),
                        contact.getMail1(), contact.getMobilePhone())));
                //, contact.getGroups().iterator().next().getName())));
                //contact.getGroups().iterator().next().getName()
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> Contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            Contacts.add(new ContactData().withName(String.format("Alex %s", i))
                    .withSurname(String.format("Family %s", i)).withMail1(String.format("@mail %s", i))
                    .withMobilePhone(String.format("888 %s", i)));
        }
        return Contacts;
    }
}