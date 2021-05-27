package Semaphore.src;

public enum PlateNumber_Enum {

    A(1 ,"粤A10110"),B(2,"川Y125OQ6"),
    C(3, "贵B10110"),D(4,"京Y125OQ6"),
    E(5,"桂c10110"),F(6,"杭D125OQ6");

    private  Integer id;
    private  String name;

    public Integer getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    PlateNumber_Enum(Integer id, String name){
        this.id=id;
        this.name=name;
    }

    public static PlateNumber_Enum getPlateNumber_Enum(Integer id){
        PlateNumber_Enum[] values = PlateNumber_Enum.values();
        for (PlateNumber_Enum e: values) {
            if (e.id==id){
                return e;
            }
        }
        return null;
    }




}
