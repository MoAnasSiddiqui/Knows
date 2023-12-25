import java.io.*;
import java.util.ArrayList;

class MyObjectOutputStream extends ObjectOutputStream {
    MyObjectOutputStream() throws IOException {
        super();
    }

    MyObjectOutputStream(OutputStream o) throws IOException {
        super(o);
    }

    public void writeStreamHeader() throws IOException {
        return;
    }
}

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

    public void readFromFile(ArrayList<T> objectList) {
        try {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() != 0) {
                @SuppressWarnings("unchecked")
                T object = (T) ois.readObject();
                objectList.add(object);

            }
            ois.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateFile(ArrayList<T> objectList) {
        // File newFile = new File(file.getAbsolutePath());
        // newFile.delete();
        for (int i = 0; i < objectList.size(); i++) {
            if (i != 0) {
                writeInFile(objectList.get(i));
            } else {
                try {
                    FileOutputStream fos = null;
                    fos = new FileOutputStream(file, false);

                    if (file.length() == 0) {
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(objectList.get(i));
                        oos.close();
                    } else {
                        MyObjectOutputStream oos = null;
                        oos = new MyObjectOutputStream(fos);
                        oos.writeObject(objectList.get(i));
                        oos.close();
                    }
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}