package Database;

import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import Api.Crypt;
import Key.KeyNode;
import Manager.Manager;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class XMLStore {

    String pathToCurrentDatabase;

    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    Document doc;

    Element user;
    Element keys;

    Manager manager;


    public XMLStore(Manager manager) throws Exception
    {
        this.dbFactory = DocumentBuilderFactory.newInstance();
        this.dBuilder = dbFactory.newDocumentBuilder();
        this.pathToCurrentDatabase = "resources//default.xml"; // default value
        this.manager = manager;
    }

    public void buildDoc()
    {
        this.doc.appendChild(this.user);
        this.user.appendChild(this.keys);
    }

    public void CreateNewDoc()
    {
        this.doc = this.dBuilder.newDocument();
        this.user = this.doc.createElement("profile");
        this.keys = this.doc.createElement("keys");
    }



    public void addKey(String username, String password, String source)
    {
        Element key = doc.createElement("key");

        Element name = doc.createElement("username");
        name.appendChild(doc.createTextNode(this.manager.encrypt(username)));

        Element pass = doc.createElement("password");
        pass.appendChild(doc.createTextNode(this.manager.encrypt(password)));

        Element info = doc.createElement("source");
        info.appendChild(doc.createTextNode(this.manager.encrypt(source)));

        key.appendChild(name);
        key.appendChild(pass);
        key.appendChild(info);
        this.keys.appendChild(key);
    }

    /*
    NOTE:
        Store data related to the user [like user's name]
     */
    public void addProfileSetting(String name, String value)
    {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(this.manager.encrypt(value)));

        this.user.appendChild(node);
    }

    public String getProfileSetting(String name)
    {
        String value = "No value!";

        try {
            File inputFile = new File(this.pathToCurrentDatabase);
            this.doc = this.dBuilder.parse(inputFile);
            this.doc.getDocumentElement().normalize();
        }
        catch (Exception ex)
        {
            System.out.println("[XML Store][Parse][Profile] Read file failed! " + ex.getMessage());
        }

        // Parse
        try {
            NodeList profile = this.doc.getElementsByTagName(name);
            if(profile.item(0).getTextContent() != null)
                value = profile.item(0).getTextContent();
        }
        catch (Exception ex)
        {
            System.out.println("[XML Store][Parse][Profile] Parse failed! " + ex.getMessage());
            ex.printStackTrace();
        }

        return value;
    }

    public void output() throws Exception
    {
        // Write to file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(this.pathToCurrentDatabase));
        transformer.transform(source, result);

        // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);

    }

    public List<KeyNode> getKeysFromDoc()
    {

        List <KeyNode> keysList = new ArrayList();

        try {
            // Set Up the functions
            File inputFile = new File(this.pathToCurrentDatabase);
            this.doc = this.dBuilder.parse(inputFile);
            this.doc.getDocumentElement().normalize();


        }
        catch (Exception ex)
        {
            System.out.println("[XML Store][Parse][Keys] Read file failed! " + ex.getMessage());
        }


        // Parse
        try {
            NodeList keysElem = this.doc.getElementsByTagName("key");

            for (int index = 0; index < keysElem.getLength(); ++index) {
                if (((Node) keysElem.item(index)).getNodeType() == Node.ELEMENT_NODE) {
                    Element key = (Element) keysElem.item(index);
                    String usernameRaw = key.getElementsByTagName("username").item(0).getTextContent();
                    String passwordRaw = key.getElementsByTagName("password").item(0).getTextContent();
                    String sourceRaw = key.getElementsByTagName("source").item(0).getTextContent();

                    System.out.println("[XML Store][Parse][Test] Extracted value: " +  usernameRaw);
                /*
                    TO-DO: Decrypt the information
                */

                    String username = this.manager.decrypt(usernameRaw);
                    String password = this.manager.decrypt(passwordRaw);
                    String source = this.manager.decrypt(sourceRaw);

                    System.out.println("[XML Store][Parse][Test] Decrypted value: " +  username);
                    keysList.add(new KeyNode(source, username, password));
                }
            }
        }
        catch (Exception ex)
        {
            System.out.println("[XML Store][Parse] Parse failed! " + ex.getMessage());
            ex.printStackTrace();
        }

        return keysList;
    }

    public void setPathToCurrentDatabase(String path)
    {
        if(path != null)
        {
            this.pathToCurrentDatabase = path;
        }
    }

    public String getPathToCurrentDatabase()
    {
        return this.pathToCurrentDatabase;
    }

}
