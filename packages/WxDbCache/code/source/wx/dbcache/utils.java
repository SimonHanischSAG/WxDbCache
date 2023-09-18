package wx.dbcache;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.lang.ns.NSName;
import com.wm.util.JournalLogger;
import com.wm.app.b2b.server.InvokeState;
import com.wm.app.b2b.server.Session;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import com.softwareag.g11n.text.DateUtils;
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void byteArrayToObject (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(byteArrayToObject)>> ---
		// @sigtype java 3.5
		// [i] object:0:required objectAsByteArray
		// [o] object:0:required object
		 IDataMap pipeMap = new IDataMap(pipeline);
		byte[] bytes = (byte[]) pipeMap.get("objectAsByteArray");
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ObjectInputStream is;
		try {
		    is = new ObjectInputStream(bis);
		    pipeMap.put("object", is.readObject());
		} catch (ClassNotFoundException | IOException e) {
		   throw new ServiceException(e);
		}
		// --- <<IS-END>> ---

                
	}



	public static final void getFormerDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getFormerDate)>> ---
		// @sigtype java 3.5
		// [i] object:0:required offset
		// [o] object:0:required formerDate
		IDataMap pipeMap = new IDataMap(pipeline);
		Integer offset = pipeMap.getAsInteger("offset");
		
		Calendar calendar = Calendar.getInstance();
		Date currentDate = calendar.getTime();
		calendar.setTime(currentDate);
		calendar.add(Calendar.SECOND, -offset);
		    
		pipeMap.put("formerDate", calendar.getTime());
		// --- <<IS-END>> ---

                
	}



	public static final void getMinDate (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(getMinDate)>> ---
		// @sigtype java 3.5
		// [o] object:0:required minDate
		IDataMap pipeMap = new IDataMap(pipeline);
		pipeMap.put("minDate", new Date(0));
		// --- <<IS-END>> ---

                
	}



	public static final void invokeAsynchronously (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(invokeAsynchronously)>> ---
		// @sigtype java 3.5
		// [i] field:0:required serviceName
		IDataMap pipeMap = new IDataMap(pipeline);
		String serviceName = pipeMap.getAsString("serviceName");
		IData pipelineClone;
		//long lastTime = System.nanoTime();
		//logTime(serviceName + " after lastTime", - lastTime + (lastTime = System.nanoTime()));
		try {
			pipelineClone = IDataUtil.deepClone(pipeline);
			//logTime("after clone", - lastTime + (lastTime = System.nanoTime()));
			
			NSName ns = NSName.create(serviceName);
			//logTime("after ns", - lastTime + (lastTime = System.nanoTime()));
			Session session = InvokeState.getCurrentSession();
			//logTime("after session", - lastTime + (lastTime = System.nanoTime()));
			Service.doThreadInvoke(ns, session, pipelineClone);
			//logTime(serviceName + " after doThreadInvoke", - lastTime + (lastTime = System.nanoTime()));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
			
		// --- <<IS-END>> ---

                
	}



	public static final void objectToByteArray (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(objectToByteArray)>> ---
		// @sigtype java 3.5
		// [i] object:0:required object
		// [o] object:0:required objectAsByteArray
		IDataMap pipeMap = new IDataMap(pipeline);
		Serializable obj = (Serializable) pipeMap.get("object");
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os;
		try {
		    os = new ObjectOutputStream(bos);
		    os.writeObject(obj);
		    pipeMap.put("objectAsByteArray", bos.toByteArray());
		} catch (IOException e) {
			 throw new ServiceException(e);
		}
		// --- <<IS-END>> ---

                
	}



	public static final void throwException (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(throwException)>> ---
		// @sigtype java 3.5
		// [i] field:0:required message
		IDataCursor pipelineCursor = pipeline.getCursor();
		
		String	msg = IDataUtil.getString( pipelineCursor, "message" );
		pipelineCursor.destroy();
		
		throw new ServiceException(msg);
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	private static void logTime(String position, long offset) {
		
		JournalLogger.log(4,  JournalLogger.FAC_FLOW_SVC, JournalLogger.ERROR, "WxDbCache", position + ": " + offset);
	}	
	// --- <<IS-END-SHARED>> ---
}

