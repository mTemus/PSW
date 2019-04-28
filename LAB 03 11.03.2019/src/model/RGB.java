package model;

public class RGB {

    private int R_value;
    private int G_value;
    private int B_value;

    public RGB() {
        setR_value(0);
        setG_value(0);
        setB_value(0);
    }

    public RGB(int r_value, int g_value, int b_value) {
        R_value = r_value;
        G_value = g_value;
        B_value = b_value;
    }

    @Override
    public String toString() {
        return "model.RGB{" +
                "R_value=" + R_value +
                ", G_value=" + G_value +
                ", B_value=" + B_value +
                '}';
    }

    public void setR_value(int r_value) {
        R_value = r_value;
    }

    public void setG_value(int g_value) {
        G_value = g_value;
    }

    public void setB_value(int b_value) {
        B_value = b_value;
    }

    public int getR_value() {
        return R_value;
    }

    public int getG_value() {
        return G_value;
    }

    public int getB_value() {
        return B_value;
    }

}
