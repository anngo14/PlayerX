package Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;

public class ListChangeListener implements ChangeListener<Boolean>{

	private Node node;
	
	public ListChangeListener(Node n) {
		node = n;
	}
	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		if(newValue)
		{
			node.setOpacity(0.8);
		}
		else
		{
			node.setOpacity(0.3);
		}	
	}

}
