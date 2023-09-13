package wx.dbcache;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.wm.util.JournalLogger;
import com.wm.app.b2b.server.InvokeState;
import com.wm.app.b2b.server.Session;
import com.wm.lang.ns.NSName;
import java.io.IOException;
import com.softwareag.util.IDataMap;
// --- <<IS-END-IMPORTS>> ---

public final class impl

{
	// ---( internal utility methods )---

	final static impl _instance = new impl();

	static impl _newInstance() { return new impl(); }

	static impl _cast(Object o) { return (impl)o; }

	// ---( server methods )---




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

	// --- <<IS-START-SHARED>> ---
	
	private static void logTime(String position, long offset) {
		
		JournalLogger.log(4,  JournalLogger.FAC_FLOW_SVC, JournalLogger.ERROR, "WxDbCache", position + ": " + offset);
	}
		
	// --- <<IS-END-SHARED>> ---
}

