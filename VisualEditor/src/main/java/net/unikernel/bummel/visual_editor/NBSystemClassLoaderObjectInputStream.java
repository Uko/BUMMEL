package net.unikernel.bummel.visual_editor;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import org.openide.util.Exceptions;

/**
 * ObjectInputStream that uses classes loaded by NB System ClassLoader.
 * So it uses classes loaded using Lookup mechanism.
 * @author mcangel
 */
public class NBSystemClassLoaderObjectInputStream extends ObjectInputStream
{
	public NBSystemClassLoaderObjectInputStream(InputStream in) throws IOException
	{
		super(in);
	}

	@Override
	public Class resolveClass(ObjectStreamClass desc) throws IOException,
			ClassNotFoundException
	{
		try
		{
			ClassLoader currentTccl = Thread.currentThread().getContextClassLoader();
			return currentTccl.loadClass(desc.getName());
		} catch (SecurityException e)
		{
      Exceptions.printStackTrace(e);
		}
		return super.resolveClass(desc);
	}
}