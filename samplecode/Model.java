import java.io.*;
import java.util.*;

public class Model implements Serializable {
  private ArrayList<Item> itemList;
  private ArrayList<Item> selectedList;
  //  list of "currently selected" items
  //private static UIContext uiContext;
  private static View view;
  public Model() {
    itemList = new ArrayList<>();
    selectedList = new ArrayList<>();
  }
  
  //  public static void setUI(UIContext uiContext) {
  //  Model.uiContext = uiContext;
  //  Item.setUIContext(uiContext);
  // }
 
  public static void setView(View view) {
    Model.view = view;
  }
  public void markSelected(Item item) {
// marks an item as selected by moving it to the
// selceted list.
    if (itemList.contains(item)) {
      itemList.remove(item);
      selectedList.add(item);
      view.refresh();
    }
  }
  public void unSelect(Item item) {
    if (selectedList.contains(item)) {
      selectedList.remove(item);
      itemList.add(item);
      view.refresh();
    }
  }

  public void deleteSelectedItems() {
    selectedList.clear();
    view.refresh();
  }
  public void addItem(Item item) {
    itemList.add(item);
    view.refresh();
  }
  public void removeItem(Item item) {
    itemList.remove(item);
    view.refresh();
  }
  public Enumeration<?> getItems() {
    return Collections.enumeration(itemList);
  }
  public void setChanged() {
    view.refresh();
  }
  public Enumeration<?> getSelectedItems() {
    return Collections.enumeration(selectedList);
  }
  // other fields, methods and classes
  public void save(String fileName) throws IOException {
    try (FileOutputStream file = new FileOutputStream(fileName);
         ObjectOutputStream output = new ObjectOutputStream(file)) {
      output.writeObject(itemList);
      output.writeObject(selectedList);
    }
  }

  @SuppressWarnings("unchecked")
  public void retrieve(String fileName) throws IOException, ClassNotFoundException {
    try (FileInputStream file = new FileInputStream(fileName);
         ObjectInputStream input = new ObjectInputStream(file)) {
      itemList = (ArrayList<Item>) input.readObject();
      selectedList = (ArrayList<Item>) input.readObject();
      view.refresh();
    }
  }

  public void readFromStream(ObjectInputStream input) throws IOException, ClassNotFoundException {
    @SuppressWarnings("unchecked")
    ArrayList<Item> tempItemList = (ArrayList<Item>) input.readObject(); // Read as raw type
    itemList = tempItemList; // Safe assignment if you trust the source

    @SuppressWarnings("unchecked")
    ArrayList<Item> tempSelectedList = (ArrayList<Item>) input.readObject(); // Read as raw type
    selectedList = tempSelectedList; // Safe assignment if you trust the source
  }
}
