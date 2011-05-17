package net.unikernel.bummel.logic_elements.visual;

import com.mxgraph.model.mxGeometry;
import net.unikernel.bummel.jgraph.ElementModel;
import net.unikernel.bummel.logic_elements.Analyzer;
import net.unikernel.bummel.logic_elements.And;
import net.unikernel.bummel.logic_elements.Generator;
import net.unikernel.bummel.logic_elements.Not;
import net.unikernel.bummel.logic_elements.Or;

/**
 *
 * @author mcangel
 */
public class VisualizationsFactory
{
	public static final ElementModel[] elements = new ElementModel[]{
		new ElementModel("Analyzer", new Analyzer(), new mxGeometry(0, 0, 100, 100), "", 
			new String[]{"D:\\ihorFolder\\Education\\LNU\\Course project\\3 course\\BUMMEL\\JGraph\\shapes\\network\\antenna.shape"})
		{
			@Override
			public void init()
			{
				System.out.println("name: "+name);
			}
		},
		new ElementModel("And", new And(), new mxGeometry(0, 0, 100, 100), ""){
			@Override
			public void init()
			{
				super.init();
			}
		},
		new ElementModel("Generator", new Generator(), new mxGeometry(0, 0, 100, 100), ""){
			@Override
			public void init()
			{
				System.out.println("name: "+name);
			}
		},
		new ElementModel("Not", new Not(), new mxGeometry(0, 0, 100, 100), ""){
			@Override
			public void init()
			{
				System.out.println("name: "+name);
			}
		},
		new ElementModel("Or", new Or(), new mxGeometry(0, 0, 100, 100), ""){
			@Override
			public void init()
			{
				System.out.println("name: "+name);
			}
		}
	};
}
