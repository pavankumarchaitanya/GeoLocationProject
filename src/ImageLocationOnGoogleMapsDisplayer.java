import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.File;
import java.io.IOException;
import java.net.URI;


public class ImageLocationOnGoogleMapsDisplayer {


	public static void main(String args[])
	{
		System.out.println(System.getProperty("user.dir"));


		File folder = new File(".//Images//");
		File[] listOfFiles = folder.listFiles();

		for(File f : listOfFiles)
		{
			if (f.isFile() ) { // not a folder 
				javaxt.io.Image image = new javaxt.io.Image(".//Images//"+ f.getName());
			System.out.println(f.getAbsolutePath());
				double[] gps = image.getGPSCoordinate();
				System.out.println("File : " + f.getName());
				for (double coordinates : gps)
				{

			//	System.out.println(  "coordinates : " + coordinates);
				String str = "https://maps.google.com/maps?q="+gps[1]+"," + gps[0];
				System.out.println(str);
				browse(str);
				}
			}
		}

	}

	 private static void browse(String url) {
		    // first try the Java Desktop
		   // logger.debug("First try the Java Desktop");
		    if (Desktop.isDesktopSupported()) {
		        Desktop desktop = Desktop.getDesktop();
		        if (desktop.isSupported(Action.BROWSE))
		            try {
		                desktop.browse(URI.create(url));
		                return;
		            } catch (IOException e) {
		                // handled below
		            }
		    }
		    // Next try rundll32 (only works on Windows)
	//	    logger.debug("Try the rundll32");
		    try {
		        Runtime.getRuntime().exec(
		                "rundll32 url.dll,FileProtocolHandler " + url);
		        return;
		    } catch (IOException e) {
		        // handled below
		    }
		    // Next try browsers
		 //   logger.debug("Try with browsers");
		//    BareBonesBrowserLaunch.openURL(url);
		}
	
}
