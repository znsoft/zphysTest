import zPhys.zField;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        zField field = new zField(20,20);
        field.update();
        zForm zform = new zForm();
        zform.InitLocalVisualWindow(field);

        while(zform.frame.isShowing()){

            field.update();
            zform.update();
        }

    }
}
