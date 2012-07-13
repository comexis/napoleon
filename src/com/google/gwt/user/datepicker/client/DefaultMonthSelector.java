/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.gwt.user.datepicker.client;

import java.util.Date;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PushButton;

/**
 * A simple {@link MonthSelector} used for the default date picker. Not
 * extensible as we wish to evolve it freely over time.
 */
@SuppressWarnings("deprecation")
public final class DefaultMonthSelector extends MonthSelector {

  private static final String[] monthsInYear = new String[12];
  private static final int NUMBER_OF_YEARS_TO_DISPLAY = 10;
  private PushButton monthBackwards;
  private PushButton monthForwards;
  private PushButton yearBackwards;
  private PushButton yearForwards;
  private ListBox monthSelect;
  private ListBox yearSelect;
  
  private Grid grid;

  /**
   * Constructor.
   */
  public DefaultMonthSelector() {
  }
 
  @Override
  protected void refresh() {
    Date current = getModel().getCurrentMonth();
    selectMonth(current);
    selectYear(current);
  }

  private void selectMonth(Date date) {
    int month = date.getMonth();
    monthSelect.setSelectedIndex(month);
  }

  private void selectYear(Date date) {    
    yearSelect.clear();
    
    int year = date.getYear();
    
    Date d = new Date();
    
    for (int i = year-NUMBER_OF_YEARS_TO_DISPLAY; i <= year+NUMBER_OF_YEARS_TO_DISPLAY; i++){
      d.setYear(i);
      yearSelect.addItem(getModel().getYearFormatter().format(d));
    }
    
    yearSelect.setSelectedIndex(NUMBER_OF_YEARS_TO_DISPLAY);
    
  }

  @Override
  protected void setup() {
    
    Date date = new Date();

    for (int i = 0; i < monthsInYear.length; ++i) {
      date.setMonth(i);
     
      monthsInYear[i] = getModel().getMonthFormatter().format(date);
    }
    
    //previous, next buttons
    monthBackwards = createNavigationButton("&lt;", -1, css().previousButton());
    monthForwards = createNavigationButton("&gt;", 1, css().nextButton());
    yearBackwards = createNavigationButton("&laquo;", -12, css().previousButton());
    yearForwards = createNavigationButton("&raquo;", 12, css().nextButton());
    
    //month and year selector
    setupMonthSelect();
    setupYearSelect();
    
    HorizontalPanel monthYearPanel = new HorizontalPanel();
    monthYearPanel.add(monthSelect);
    monthYearPanel.add(yearSelect);
    
    // Set up grid.
    grid = new Grid(1, 5);
    grid.setWidget(0, 0, yearBackwards);
    grid.setWidget(0, 1, monthBackwards);
    grid.setWidget(0, 2, monthYearPanel);
    grid.setWidget(0, 3, monthForwards);
    grid.setWidget(0, 4, yearForwards);

    CellFormatter formatter = grid.getCellFormatter();
    formatter.setStyleName(0, 2, css().month());
    formatter.setWidth(0, 0, "1");
    formatter.setWidth(0, 1, "1");
    formatter.getElement(0, 1).getStyle().setFontSize(90, Unit.PCT);
    formatter.setWidth(0, 2, "100%");
    formatter.setWidth(0, 3, "1");
    formatter.getElement(0, 3).getStyle().setFontSize(90, Unit.PCT);
    formatter.setWidth(0, 4, "1");
    
    grid.setStyleName(css().monthSelector());
    initWidget(grid);
  }
  
  private void setupMonthSelect() {
    monthSelect = new ListBox();
    
    for (String month : monthsInYear){
      monthSelect.addItem(month);
    }
    
    monthSelect.addChangeHandler(new ChangeHandler() {
      
      @Override
      public void onChange(ChangeEvent event) {
        int previousMonth = getModel().getCurrentMonth().getMonth();
        int newMonth = monthSelect.getSelectedIndex();
        int delta = newMonth - previousMonth;
        
        addMonths(delta);
        
      }
    });
    
  }

  private void setupYearSelect() {
   yearSelect = new ListBox();
   
   yearSelect.addChangeHandler(new ChangeHandler() {
    
    @Override
    public void onChange(ChangeEvent event) {
      int delta = yearSelect.getSelectedIndex() - NUMBER_OF_YEARS_TO_DISPLAY;
      addMonths(delta * 12);
      
    }
  });
  }

  private PushButton createNavigationButton(String label, final int nbrOfMonth, String cssClass){
    
      PushButton button = new PushButton();
     
      button.addClickHandler(new ClickHandler() {
        public void onClick(ClickEvent event) {
          addMonths(nbrOfMonth);
        }
      });

      button.getUpFace().setHTML(label);
      button.setStyleName(cssClass);
      
      return button;
    
  }

}