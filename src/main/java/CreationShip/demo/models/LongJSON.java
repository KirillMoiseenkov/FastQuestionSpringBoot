package CreationShip.demo.models;

public class LongJSON
{

    private Long value;

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public LongJSON()
    {}

    @Override
    public String toString() {
        return "LongJSON{" +
                "id=" + value +
                '}';
    }
}
