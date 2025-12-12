package com.cryptobroz.gnosis_tax_tool.enums;

public enum SelectedYear {
  CURRENT,
  PREVIOUS;

  @Override
  public String toString() {
    return String.format("%s, %d",
        this == CURRENT ? "Current Year" : "Previous Year",
        this.toInt());
  }

  public int toInt() {
    final int currentYear = java.time.Year.now().getValue();
    if (this == PREVIOUS) {
      return currentYear - 1;
    }
    return currentYear;
  }
}