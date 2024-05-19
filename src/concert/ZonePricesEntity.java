package concert;

public class ZonePricesEntity {
    private int leftPrice;
    private int centerPrice;
    private int rightPrice;

    public ZonePricesEntity(int leftPrice, int centerPrice, int rightPrice) {
        this.leftPrice = leftPrice;
        this.centerPrice = centerPrice;
        this.rightPrice = rightPrice;
    }

    public int getLeftPrice() {
        return leftPrice;
    }

    public void setLeftPrice(int leftPrice) {
        this.leftPrice = leftPrice;
    }

    public int getCenterPrice() {
        return centerPrice;
    }

    public void setCenterPrice(int centerPrice) {
        this.centerPrice = centerPrice;
    }

    public int getRightPrice() {
        return rightPrice;
    }

    public void setRightPrice(int rightPrice) {
        this.rightPrice = rightPrice;
    }
}
