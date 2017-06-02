package view.JTableModels;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import app.App;
import controller.Event;
import controller.Vip;
import model.DAOEvent;

public class EventJTable extends AbstractTableModel{
	private List<Event> eventList;
	private final String[] columnHeads = {"Vip 1", "Vip 2", "Wedding date", "Wedding place", "Divorce date"};
	private DAOEvent dao;
	
	public EventJTable() throws SQLException{
		eventList = new ArrayList<Event>();
		this.dao=App.getDaoEvent();
		dao.getEvents(eventList);
	}
	
	@Override
	public String getColumnName(int col){
		return columnHeads[col];
	}
	
	@Override
	public int getRowCount(){
		return eventList.size();
	}
	
	@Override
	public int getColumnCount(){
		return columnHeads.length;
	}
	
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex){
		switch(columnIndex){
		case 0:
			return eventList.get(rowIndex).getIdVip1();
		case 1:
			return eventList.get(rowIndex).getIdVip2();
		case 2:
			return eventList.get(rowIndex).getWeddingDate();
		case 3:
			return eventList.get(rowIndex).getWeddingPlace();
		case 4:
			return eventList.get(rowIndex).getDivorceDate();
		}
		return null;
	}
}
