package base;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.imageio.ImageIO;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Screenshot extends BaseFiles {
	
	public void FullPageScreenShot(String folderPath) throws InterruptedException, IOException {
        
        //File Format
		LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MMM-yyyy hh.mm.ss a");
        String formattedDate = myDateObj.format(myFormatObj);
        
        //Delay to load the screen
        Thread.sleep(5000);

        //Full Page Screenshot setup
        AShot aShot = new AShot();
        ru.yandex.qatools.ashot.Screenshot fullPage = aShot
                .shootingStrategy(ShootingStrategies.viewportPasting(2000))
                .takeScreenshot(driver);
        
        // Save the screenshot
        File screenshotFile = new File(folderPath + "\\" + formattedDate + ".jpeg");
        ImageIO.write(fullPage.getImage(), "JPEG", screenshotFile);

        System.out.println("Screenshot saved as: " + screenshotFile.getAbsolutePath());
        
    }
	
}
