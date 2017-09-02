import zPhys.zField;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        zField field = new zField(10,10);
        field.update();
        zForm zform = new zForm();
        zform.InitLocalVisualWindow(field);

        while(true){
            field.update();
            zform.update();
        }

    }
}
