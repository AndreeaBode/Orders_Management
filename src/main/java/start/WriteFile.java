package start;
import java.io.*;

public class WriteFile {
    File file3 = new File("fisier4.txt");

    /**
     * constructorul clasei
     */
    public WriteFile() {

    }

    /**
     * metoda care creaza fisierul
     * @throws IOException
     */
    public void create() throws IOException {
        this.file3.createNewFile();
    }

    /**
     * metoda care realizeaza scrierea in fisier
     * @param name
     * @throws IOException
     */
    public void writeInFile(String name) throws IOException {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\andre\\OneDrive\\Desktop\\PT\\PT2022_30228_Bode_Andreea_Nicoleta_Assignment_3\\fisier4.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.append(name);
            bufferedWriter.newLine();
            bufferedWriter.close();
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


}
