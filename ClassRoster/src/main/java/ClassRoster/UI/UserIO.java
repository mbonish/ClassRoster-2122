package ClassRoster.UI;

public interface UserIO {
    void print(String var1);

    double readDouble(String var1);

    double readDouble(String var1, double var2, double var4);

    float readFloat(String var1);

    float readFloat(String var1, float var2, float var3);

    int readInt(String var1);

    int readInt(String var1, int var2, int var3);

    long readLong(String var1);

    long readLong(String var1, long var2, long var4);

    String readString(String var1);
}