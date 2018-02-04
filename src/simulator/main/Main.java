package simulator.main;

import com.glutilities.ui.GLWindow;

public class Main {

	public static void main(String[] args) {
		GLWindow.initGLFW();

//		ValveModelLoader loader = new ValveModelLoader();
//		try {
//			loader.load(new File("res/models/rails/models/shinji85/train/rail_1x.vvd"), "rail_1x");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		//System.exit(0);

		GLWindow window = new GLWindow(800, 600, "Train Simulator", 0);
		window.setMasterRenderer(new GameRenderer());
		window.create();
		window.init();

		window.makeContextCurrent();
		window.loop();
		window.delete();

		GLWindow.terminateGLFW();
	}

}
