/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_project;

/**
 * @author aon_c
 * 
 * This class is Controller class.
 * Use for handle command.
 */
public class ControlCore {
    private DataCore data;
    private DisplayCore display;
    private ServiceCore service;

    public void setData(DataCore data) {
        this.data = data;
    }

    public void setDisplay(DisplayCore display) {
        this.display = display;
    }

    public void setService(ServiceCore service) {
        this.service = service;
    }
    
    
    public void  init(){
    data.init();
    service.init();
    display.init();
    
    display.addSprite(data.getCharr("knigth_idle_1"));
    }
}
