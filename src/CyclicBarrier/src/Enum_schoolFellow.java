package CyclicBarrier.src;

public enum Enum_schoolFellow {

    ONE(1,"小明"),TWO(2,"小王"),
    THREE(3,"小郭"),FUR(4,"小赵"),
    FIVE(5,"小伟"),SIX(6,"韩韩");
    private int id;
    private  String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Enum_schoolFellow(int id ,String name){
        this.id=id;
        this.name=name;
    }


    public static Enum_schoolFellow   find_SchoolFellow(int i){
        Enum_schoolFellow[] values = Enum_schoolFellow.values();
        for (Enum_schoolFellow e : values) {
            if (i==e.getId()){
                return e;
            }

        }

        return null;
    }
}
