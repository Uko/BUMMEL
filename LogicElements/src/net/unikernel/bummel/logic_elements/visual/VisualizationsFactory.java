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
		new ElementModel("Analyzer", new Analyzer(), new mxGeometry(0, 0, 30, 18), "", 
			new String[]{" <?xml version=\"1.0\" encoding=\"UTF-8\"?> <shape xmlns=\"http://www.daa.com.au/~james/dia-shape-ns\" xmlns:svg=\"http://www.w3.org/2000/svg\"> <name>images - analyzer0</name> <icon>analyzer0.png</icon> <connections> <point x=\"12\" y=\"9\"/> <point x=\"12\" y=\"3\"/> <point x=\"9\" y=\"6\"/> <point x=\"15\" y=\"6\"/> <point x=\"5\" y=\"6\"/> <point x=\"9\" y=\"6\"/> <point x=\"7\" y=\"6\"/> </connections> <aspectratio type=\"fixed\"/> <svg:svg> <svg:ellipse style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" cx=\"12\" cy=\"6\" rx=\"3\" ry=\"3\"/> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"5\" y1=\"6\" x2=\"9\" y2=\"6\"/> </svg:svg> </shape>",
						 " <?xml version=\"1.0\" encoding=\"UTF-8\"?> <shape xmlns=\"http://www.daa.com.au/~james/dia-shape-ns\" xmlns:svg=\"http://www.w3.org/2000/svg\"> <name>images - analyzer1</name> <icon>analyzer1.png</icon> <connections> <point x=\"12\" y=\"9\"/> <point x=\"12\" y=\"3\"/> <point x=\"9\" y=\"6\"/> <point x=\"15\" y=\"6\"/> <point x=\"5\" y=\"6\"/> <point x=\"9\" y=\"6\"/> <point x=\"7\" y=\"6\"/> </connections> <aspectratio type=\"fixed\"/> <svg:svg> <svg:ellipse style=\"fill: #ff0000\" cx=\"12\" cy=\"6\" rx=\"3\" ry=\"3\"/> <svg:ellipse style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" cx=\"12\" cy=\"6\" rx=\"3\" ry=\"3\"/> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"5\" y1=\"6\" x2=\"9\" y2=\"6\"/> </svg:svg> </shape> "})
		{
			@Override
			public void init()
			{
				System.out.println("name: "+name);
			}
		},
		new ElementModel("And", new And(), new mxGeometry(0, 0, 56, 40), "", new String[]{" <?xml version=\"1.0\" encoding=\"UTF-8\"?> <shape xmlns=\"http://www.daa.com.au/~james/dia-shape-ns\" xmlns:svg=\"http://www.w3.org/2000/svg\"> <name>images - and</name> <icon>and.png</icon> <connections> <point x=\"12.125\" y=\"6.5\"/> <point x=\"15\" y=\"6.5\"/> <point x=\"13.5625\" y=\"6.5\"/> <point x=\"3.8\" y=\"4.5\"/> <point x=\"6.3\" y=\"4.5\"/> <point x=\"5.05\" y=\"4.5\"/> <point x=\"3.8\" y=\"8.5\"/> <point x=\"6.3\" y=\"8.5\"/> <point x=\"5.05\" y=\"8.5\"/> </connections> <aspectratio type=\"fixed\"/> <svg:svg> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"12.125\" y1=\"6.5\" x2=\"15\" y2=\"6.5\"/> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"3.8\" y1=\"4.5\" x2=\"6.3\" y2=\"4.5\"/> <svg:path style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" d=\"M 6.5 2.5 C 14,2.5 14,10.5 6.5,10.5 C 6.5,2.5 6.5,10.5 6.5,2.5\"/> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"3.8\" y1=\"8.5\" x2=\"6.3\" y2=\"8.5\"/> </svg:svg> </shape>"}){
			@Override
			public void init()
			{
				super.init();
			}
		},
		new ElementModel("Generator", new Generator(), new mxGeometry(0, 0, 41, 50), "", new String[]{" <?xml version=\"1.0\" encoding=\"UTF-8\"?> <shape xmlns=\"http://www.daa.com.au/~james/dia-shape-ns\" xmlns:svg=\"http://www.w3.org/2000/svg\"> <name>images - generator</name> <icon>generator.png</icon> <connections> <point x=\"11.25\" y=\"8\"/> <point x=\"13.5\" y=\"8\"/> <point x=\"12.375\" y=\"8\"/> <point x=\"10.5\" y=\"6\"/> <point x=\"8\" y=\"7.5\"/> <point x=\"10\" y=\"8.5\"/> <point x=\"8.92328\" y=\"9.57672\"/> <point x=\"9.25\" y=\"6.75\"/> <point x=\"9\" y=\"8\"/> <point x=\"9.46164\" y=\"9.03836\"/> <point x=\"8.65811\" y=\"9.84189\"/> <point x=\"8.83489\" y=\"9.31156\"/> <point x=\"8.92328\" y=\"9.57672\"/> <point x=\"9.18844\" y=\"9.66511\"/> <point x=\"8.7465\" y=\"9.57672\"/> <point x=\"8.87908\" y=\"9.44414\"/> <point x=\"9.05586\" y=\"9.62092\"/> </connections> <aspectratio type=\"fixed\"/> <svg:svg> <svg:path style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" d=\"M 7 4.33333 C 7.85,3.33333 8.275,3 9.125,3 C 9.975,3 10.4,3.33333 11.25,4.33333 L 11.25,9.66667 C 10.4,10.6667 9.975,11 9.125,11 C 8.275,11 7.85,10.6667 7,9.66667 L 7,4.33333\"/> <svg:path style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" d=\"M 7 4.33333 C 7.85,5.33333 8.275,5.66667 9.125,5.66667 C 9.975,5.66667 10.4,5.33333 11.25,4.33333\"/> <svg:text style=\"fill: #000000; font-size: 0.8; text-anchor:middle; font-family: sans; font-style: normal; font-weight: normal\" x=\"9.125\" y=\"7.86667\" textLength=\"0\"></svg:text> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"11.25\" y1=\"8\" x2=\"13.5\" y2=\"8\"/> <svg:polyline style=\"fill: none; fill-opacity:0; stroke-width: 0.2; stroke: #000000\" points=\"10.5,6 8,7.5 10,8.5 8.92328,9.57672 \"/> <svg:polygon style=\"fill: #000000\" points=\"8.65811,9.84189 8.83489,9.31156 8.92328,9.57672 9.18844,9.66511 \"/> <svg:polygon style=\"fill: none; fill-opacity:0; stroke-width: 0.2; stroke: #000000\" points=\"8.65811,9.84189 8.83489,9.31156 8.92328,9.57672 9.18844,9.66511 \"/> </svg:svg> </shape> "}){
			@Override
			public void init()
			{
				System.out.println("name: "+name);
			}
		},
		new ElementModel("Not", new Not(), new mxGeometry(0, 0, 63, 40), "", new String[]{" <?xml version=\"1.0\" encoding=\"UTF-8\"?> <shape xmlns=\"http://www.daa.com.au/~james/dia-shape-ns\" xmlns:svg=\"http://www.w3.org/2000/svg\"> <name>images - not</name> <icon>not.png</icon> <connections> <point x=\"1\" y=\"1\"/> <point x=\"7\" y=\"5\"/> <point x=\"1\" y=\"9\"/> <point x=\"4\" y=\"3\"/> <point x=\"4\" y=\"7\"/> <point x=\"7.7\" y=\"5.55\"/> <point x=\"7.7\" y=\"4.45\"/> <point x=\"7.15\" y=\"5\"/> <point x=\"8.25\" y=\"5\"/> <point x=\"8.42929\" y=\"5\"/> <point x=\"10.9293\" y=\"5\"/> <point x=\"9.67929\" y=\"5\"/> <point x=\"-1.67175\" y=\"5\"/> <point x=\"0.828249\" y=\"5\"/> <point x=\"-0.421751\" y=\"5\"/> </connections> <aspectratio type=\"fixed\"/> <svg:svg> <svg:polygon style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" points=\"1,1 7,5 1,9 \"/> <svg:ellipse style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" cx=\"7.7\" cy=\"5\" rx=\"0.55\" ry=\"0.55\"/> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"8.42929\" y1=\"5\" x2=\"10.9293\" y2=\"5\"/> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"-1.67175\" y1=\"5\" x2=\"0.828249\" y2=\"5\"/> </svg:svg> </shape> "}){
			@Override
			public void init()
			{
				System.out.println("name: "+name);
			}
		},
		new ElementModel("Or", new Or(), new mxGeometry(0, 0, 52, 40), "", new String[]{" <?xml version=\"1.0\" encoding=\"UTF-8\"?> <shape xmlns=\"http://www.daa.com.au/~james/dia-shape-ns\" xmlns:svg=\"http://www.w3.org/2000/svg\"> <name>images - or</name> <icon>or.png</icon> <connections> <point x=\"11.125\" y=\"6\"/> <point x=\"14\" y=\"6\"/> <point x=\"12.5625\" y=\"6\"/> <point x=\"4\" y=\"4\"/> <point x=\"7\" y=\"4\"/> <point x=\"5.5\" y=\"4\"/> <point x=\"4\" y=\"8\"/> <point x=\"7\" y=\"8\"/> <point x=\"5.5\" y=\"8\"/> </connections> <aspectratio type=\"fixed\"/> <svg:svg> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"11.125\" y1=\"6\" x2=\"14\" y2=\"6\"/> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"4\" y1=\"4\" x2=\"7\" y2=\"4\"/> <svg:path style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" d=\"M 5.5 2 C 13,2 13,10 5.5,10 C 8,8 8,4 5.5,2\"/> <svg:line style=\"fill: none; fill-opacity:0; stroke-width: 0.4; stroke: #000000\" x1=\"4\" y1=\"8\" x2=\"7\" y2=\"8\"/> </svg:svg> </shape> "}){
			@Override
			public void init()
			{
				System.out.println("name: "+name);
			}
		}
	};
}
