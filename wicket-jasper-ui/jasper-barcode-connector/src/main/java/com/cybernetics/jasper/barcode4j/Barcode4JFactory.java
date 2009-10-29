
package com.cybernetics.jasper.barcode4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

/**
 * Generates barcode using barcode4j API
 * @author Emmanuel Nollase - emanux
 * created 2009 10 29 - 17:54:15
 */
public final class Barcode4JFactory
{

    private Barcode4JFactory()
    {
    }

    public static byte[] generateCode128(String barcode, double height)
    {
	byte[] datas = null;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	Code128Bean bean = new Code128Bean();
	BitmapCanvasProvider canvas = new BitmapCanvasProvider(out,"image/jpeg", 150, 12, false, 0);
	//int dpi = 150;
	
	bean.setModuleWidth(UnitConv.in2mm(0.0066666668280959129D));
	bean.setHeight(height);
	bean.doQuietZone(false);
	bean.generateBarcode(canvas, barcode);
	
	try
	{
	    canvas.finish();
	    datas = out.toByteArray();
	} 
	catch (IOException e)
	{
	    e.printStackTrace();
	}
	finally
	{
	    try
	    {
		out.close();
	    } catch (IOException e)
	    {
		// ignore
	    }
	}

	return datas;
    }
}
