package com.codegenerator.common;

import java.io.*;

public class InputStreamMonitor extends Thread {
	private InputStream inputStream;

	public InputStreamMonitor(InputStream is) {
		inputStream = is;
		this.start();
	}

	public void run() {
		try {
			while (true) {
				int value = inputStream.read();
				if (value == -1) {
					// this.stop();
					return;
				} else {
					System.out.write(value);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			// this.stop();
			return;
		}
	}
}
