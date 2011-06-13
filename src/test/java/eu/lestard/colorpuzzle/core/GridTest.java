package eu.lestard.colorpuzzle.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import eu.lestard.colorpuzzle.util.ColorChooser;



public class GridTest {
	
	ColorChooser colorChooserMock;
	
	@Before
	public void setUp(){
		colorChooserMock = EasyMock.createMock(ColorChooser.class);
		
		EasyMock.expect(colorChooserMock.getColor()).andReturn(Color.red);
		EasyMock.expectLastCall().anyTimes();
		
		
		EasyMock.replay(colorChooserMock);
		
		
	}
	
	@Test
	public void testFill(){
		int height = 5;
		int width = 5;
		
		Grid grid = new Grid(width, height, colorChooserMock);
		
		
		for(int i=0 ; i<width ; i++){
			for(int j=0 ; j<height ; j++){
				
				Piece temp = grid.getPiece(i,j);
				
				assertNotNull(temp);
				assertNotNull(temp.getColor());
				
				
			}
		}
		
		assertNull(grid.getPiece(width, height + 1));
		assertNull(grid.getPiece(width + 1, height));
		
		assertEquals("size", (height*width), grid.size());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridConstructorFail1(){
		
		//The width is 0. This should throw a IllegalArgumentException
		Grid grid = new Grid(0,10,colorChooserMock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridConstructorFail2(){
		//Now the width is negative
		Grid grid = new Grid(-20,10,colorChooserMock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridConstructorFail3(){
		//Height is 0.
		Grid grid = new Grid(10,0,colorChooserMock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGridConstructorFail4(){
		//Now height is negative
		Grid grid = new Grid(10,-20,colorChooserMock);
	}
	
	@Test(expected=NullPointerException.class)
	public void testGridContructorFailColorChooserIsNull(){
		Grid grid = new Grid(10,10,null);
	}

}
