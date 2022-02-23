package wx.dbcache;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
}

