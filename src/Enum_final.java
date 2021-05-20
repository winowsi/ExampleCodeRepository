
public enum Enum_final {

   ONE(1,"齐国"),TWO(2,"楚国"),THREE(3,"燕国"),FUR(4,"赵国"),FIVE(5,"魏国"),SIX(6,"韩国");

   private Integer id;
   private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Enum_final(Integer id, String name) {
        this.id = id;
        this.name = name;
    }



    public static  Enum_final forEach_Enum_final(int index){
        Enum_final[] values = Enum_final.values();
        for (Enum_final e:values) {
            if (index==e.getId()){
                return e;
            }
        }
        return null;
    }

}
