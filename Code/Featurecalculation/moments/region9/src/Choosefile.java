package p2;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class Choosefile
{

  public String[] choose()
  {
  String s[];
  s=new String [2];
    JFileChooser fileChooser = new JFileChooser(".");
    FileFilter filter1 = new ExtensionFileFilter("JPG and JPEG", new String[] { "JPG", "JPEG" });
    fileChooser.setFileFilter(filter1);
    int status = fileChooser.showOpenDialog(null);
    if (status == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      s[0]=selectedFile.getParent();
      s[1]=s[0]+"/"+selectedFile.getName();
     // System.out.println(s[0]);
      //System.out.println(s[1]);
    } else if (status == JFileChooser.CANCEL_OPTION) {
      System.out.println(JFileChooser.CANCEL_OPTION);
    }
    return s;
  }
}

class ExtensionFileFilter extends FileFilter {
  String description;

  String extensions[];

  public ExtensionFileFilter(String description, String extension) {
    this(description, new String[] { extension });
  }

  public ExtensionFileFilter(String description, String extensions[]) {
    if (description == null) {
      this.description = extensions[0];
    } else {
      this.description = description;
    }
    this.extensions = (String[]) extensions.clone();
    toLower(this.extensions);
  }

  private void toLower(String array[]) {
    for (int i = 0, n = array.length; i < n; i++) {
      array[i] = array[i].toLowerCase();
    }
  }

  public String getDescription() {
    return description;
  }

  public boolean accept(File file) {
    if (file.isDirectory()) {
      return true;
    } else {
      String path = file.getAbsolutePath().toLowerCase();
      for (int i = 0, n = extensions.length; i < n; i++) {
        String extension = extensions[i];
        if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
          return true;
        }
      }
    }
    return false;
  }
}
