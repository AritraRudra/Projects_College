package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Logger;

import net.sf.sevenzipjbinding.ExtractAskMode;
import net.sf.sevenzipjbinding.ExtractOperationResult;
import net.sf.sevenzipjbinding.IArchiveExtractCallback;
import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.ISevenZipInArchive;
import net.sf.sevenzipjbinding.PropID;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;

public class SevenZipJBindingExtractor {
	// private static Logger logger = Logger.getLogger(SevenZipJBindingExtractor.class.getName());

	//public void extract(String file, String extractPath)throws SevenZipException, IOException {
	public void extract(String extractPath)throws SevenZipException, IOException {
		String file="2.0.5.win64";
		//String extractPath="E:\\Projects\\Online_Video_Chat\\By_Others\\temp\\Unpacking_JARs\\7zip_LZMA\\";
		ISevenZipInArchive inArchive = null;
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(new File(file), "r");
			inArchive = SevenZip.openInArchive(null,new RandomAccessFileInStream(randomAccessFile));
			inArchive.extract(null, false, new MyExtractCallback(inArchive,extractPath));
		} finally {
			if (inArchive != null) {
				inArchive.close();
			}
			if (randomAccessFile != null) {
				randomAccessFile.close();
			}
		}
	}

	private static class MyExtractCallback implements IArchiveExtractCallback {
		private final ISevenZipInArchive inArchive;
		private final String extractPath;

		public MyExtractCallback(ISevenZipInArchive inArchive, String extractPath) {
			this.inArchive = inArchive;
			// this.extractPath = StringUtils.ensurePathEndsInSlash(extractPath); //Modified
			this.extractPath = extractPath;
		}

		@Override
		public ISequentialOutStream getStream(final int index,ExtractAskMode extractAskMode) throws SevenZipException {
			return new ISequentialOutStream() {
				@Override
				public int write(byte[] data) throws SevenZipException {
					String filePath = inArchive.getStringProperty(index, PropID.PATH);
					FileOutputStream fos = null;
					try {
						File path = new File(extractPath + filePath);

						if (!path.getParentFile().exists()) {
							path.getParentFile().mkdirs();
						}

						if (!path.exists()) {
							path.createNewFile();
						}

						fos = new FileOutputStream(path, true);
						fos.write(data);
					} catch (IOException e) {
						// logger.error("IOException while extracting "+filePath, e);
					} finally {
						try {
							if (fos != null) {
								fos.flush();
								fos.close();
							}
						} catch (IOException e) {
							// logger.error("Could not close FileOutputStream", e);
						}
					}
					return data.length;
				}
			};
		}

		@Override
		public void prepareOperation(ExtractAskMode extractAskMode)	throws SevenZipException {}

		@Override
		public void setOperationResult(ExtractOperationResult extractOperationResult)throws SevenZipException {}

		@Override
		public void setCompleted(long completeValue) throws SevenZipException {}

		@Override
		public void setTotal(long total) throws SevenZipException {}
	}

	/*
	 public static void main(String[] args) throws Exception { if (args.length == 0) {
	 	System.out.println("Usage: java SevenZipJBindingExtractor file extractPath");
	 		return;
	 	}
	 	new SevenZipJBindingExtractor().extract(args[0], args[1]); 
	 }
	  */
}
