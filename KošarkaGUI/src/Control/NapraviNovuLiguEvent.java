package Control;

import Model.DataWork.DataBase;
import Model.Objects.AbsLiga;
import Model.Objects.LigaGrupe;
import Model.Objects.LigaTabela;
import View.NapraviLiguView;
import View.NapraviNoviTimWindow;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class NapraviNovuLiguEvent implements EventHandler<MouseEvent> {
    private NapraviLiguView view;
    private String tip;
    private AbsLiga liga;

    public NapraviNovuLiguEvent(NapraviLiguView view, String tip) {
        this.view = view;
        this.tip = tip;
    }

    @Override
    public void handle(MouseEvent event) {
        try {
            String info1 = view.getImeLigeField().getText();
            int info2 = Integer.parseInt(view.getBrojOneField().getText());
            int info3 = Integer.parseInt(view.getBrojTwoField().getText());
            if(tip.equals("Liga sa Grupama")){
                liga=new LigaGrupe(info1,info2,info3);

            }else{
                liga=new LigaTabela(info1,info2,info3);
            }
            view.setLiga(liga);
            DataBase.getInstance().addLiga(liga);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        new NapraviNoviTimWindow(view.getLiga());
    }
}
