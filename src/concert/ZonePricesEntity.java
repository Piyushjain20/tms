package concert;

public class ZonePricesEntity {
    private float leftPrice;
    private float centerPrice;
    private float rightPrice;

    public ZonePricesEntity(float leftPrice, float centerPrice, float rightPrice) {
        this.leftPrice = leftPrice;
        this.centerPrice = centerPrice;
        this.rightPrice = rightPrice;
    }

    public float getLeftPrice() {
        return leftPrice;
    }

    public void setLeftPrice(float leftPrice) {
        this.leftPrice = leftPrice;
    }

    public float getCenterPrice() {
        return centerPrice;
    }

    public void setCenterPrice(float centerPrice) {
        this.centerPrice = centerPrice;
    }

    public float getRightPrice() {
        return rightPrice;
    }

    public void setRightPrice(float rightPrice) {
        this.rightPrice = rightPrice;
    }
}
