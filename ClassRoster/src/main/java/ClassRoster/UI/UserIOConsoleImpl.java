package ClassRoster.UI;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
    Scanner sc;

    public UserIOConsoleImpl() {
        this.sc = new Scanner(System.in);
    }

    public void print(String message) {
        System.out.println(message);
    }

    public double readDouble(String prompt) {
        this.print(prompt);
        return Double.parseDouble(this.sc.nextLine());
    }

    public double readDouble(String prompt, double min, double max) {
        double num;
        do {
            this.print(prompt);
            num = Double.parseDouble(this.sc.nextLine());
        } while(num < min || num > max);

        return num;
    }

    public float readFloat(String prompt) {
        this.print(prompt);
        return Float.parseFloat(this.sc.nextLine());
    }

    public float readFloat(String prompt, float min, float max) {
        float num;
        do {
            this.print(prompt);
            num = Float.parseFloat(this.sc.nextLine());
        } while(num < min || num > max);

        return num;
    }

    public int readInt(String prompt) {
        this.print(prompt);
        return Integer.parseInt(this.sc.nextLine());
    }

    public int readInt(String prompt, int min, int max) {
        int num;
        do {
            this.print(prompt);
            num = Integer.parseInt(this.sc.next());
        } while(num < min || num > max);

        return num;
    }

    public long readLong(String prompt) {
        this.print(prompt);
        return Long.parseLong(this.sc.nextLine());
    }

    public long readLong(String prompt, long min, long max) {
        long num;
        do {
            this.print(prompt);
            num = Long.parseLong(this.sc.nextLine());
        } while(num < min || num > max);

        return num;
    }

    public String readString(String prompt) {
        this.print(prompt);
        return this.sc.nextLine();
    }
}
