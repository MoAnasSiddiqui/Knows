import java.io.*;

public class ObjectHandling<T> {
    private File file;

    ObjectHandling(String fileName) {
        file = new File(fileName);
    }

    public void writeInFile(T object) {
        try {
            FileOutputStream fos = null;
            fos = new FileOutputStream(file, true);

            if (file.length() == 0) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(object);
                oos.close();
            } else {

                MyObjectOutputStream oos = null;
                oos = new MyObjectOutputStream(fos);
                oos.writeObject(object);
                oos.close();
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() != 0) {
                @SuppressWarnings("unchecked")
                T object = (T) ois.readObject();
                System.out.print(object.toString());

            }
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}