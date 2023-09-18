package wx.dbcache;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Map.Entry;
import java.util.Set;
import com.softwareag.util.IDataMap;
import com.wm.app.b2b.server.InvokeState;
import com.wm.app.b2b.server.ServerAPI;
import com.wm.data.IData;
import com.wm.data.IDataCursor;
import com.wm.data.IDataUtil;
// --- <<IS-END-IMPORTS>> ---

public final class utils

{
	// ---( internal utility methods )---

	final static utils _instance = new utils();

	static utils _newInstance() { return new utils(); }

	static utils _cast(Object o) { return (utils)o; }

	// ---( server methods )---




	public static final void sleep (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sleep)>> ---
		// @sigtype java 3.5
		// [i] field:0:required sleepTimeInMilliSeconds
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		Integer sleepTimeInMilliSeconds = IDataUtil.getInt(pipelineCursor,
				"sleepTimeInMilliSeconds", -1);
		pipelineCursor.destroy();
		
		if (sleepTimeInMilliSeconds != -1){
			try {
				Thread.sleep(sleepTimeInMilliSeconds);
			} catch (InterruptedException e) {
				throw new ServiceException(e);
			}
		}
		
		
		
		
		
			
			
		// --- <<IS-END>> ---

                
	}
}

