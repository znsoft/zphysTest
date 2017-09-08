import zPhys.Controll;
import zPhys.zField;

public class Main {

    public static void main(String[] args) {
//        System.out.println("Hello World!");
        zField field = new zField(20,20);
//        field.update();
        zForm zform = new zForm();
        zform.InitLocalVisualWindow(field);

        toolbox dialog = new toolbox();
        dialog.pack();
        dialog.setVisible(true);

        while(zform.frame.isShowing()){

            if(!Controll.isPause)field.update();
            zform.update();
        }

        dialog.dispose();
    }
}
