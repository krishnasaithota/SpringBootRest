package com.foodapp.springrest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.foodapp.springrest.entity.ReceipeEntity;
import com.foodapp.springrest.model.Ingredients;
import com.foodapp.springrest.model.Receipe;
import com.foodapp.springrest.service.ReceipeService;

@RunWith(SpringJUnit4ClassRunner.class)

public class ReceipeServiceTest {

	private static final Logger logger = LogManager.getLogger(ReceipeServiceTest.class);

	@Mock
	private ReceipeService receipeService;
 
	private MockMvc mockMvc;

	List<ReceipeEntity> receipeMOList;
	Receipe _receipeVO_withValidRequest;
	ReceipeEntity reciepeEntity;
	List<Receipe> receipes;
	String arryOfIngredients;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(receipeService).build();

		receipeMOList = Arrays.asList(
				
				new ReceipeEntity(1, "Crock Pot Caramelized Onions", "http://www.recipezaar.com/Crock-Pot- Caramelized-Onions-191625", "http://img.recipepuppy.com/ 338845.jpg", Arrays.asList("butter","onions"), true),
				new ReceipeEntity(2, "Pulled Chicken Sandwiches (Crock Pot)", "http://www.recipezaar.com/Pulled-Chicken- Sandwiches-Crock-Pot-242547", "http://img.recipepuppy.com/ 107122.jpg", Arrays.asList("chicken","onions"), true),
				new ReceipeEntity(3, "Bruschetta With Roasted Garlic and Cherry Tomatoes", "http://www.recipezaar.com/Bruschetta-With- Roasted-Garlic-and-Cherry-Tomatoes-244281", "http://img.recipepuppy.com/ 199304.jpg", Arrays.asList("garlic","italian bread"), true),
				new ReceipeEntity(4, "Garlic Vinegar", "http://www.recipezaar.com/Garlic- Vinegar-251602", "http://img.recipepuppy.com/ 647882.jpg", Arrays.asList("garlic","vinegar"), true));

		
		
		receipes= Arrays.asList(new Receipe( "Crock Pot Caramelized Onions","http://img.recipepuppy.com/ 338845.jpg", "http://www.recipezaar.com/Crock-Pot- Caramelized-Onions-191625", new String[] {"butter","onions"}),
				new Receipe( "Pulled Chicken Sandwiches (Crock Pot)","http://img.recipepuppy.com/ 107122.jpg", "http://www.recipezaar.com/Pulled-Chicken- Sandwiches-Crock-Pot-242547", new String[] {"chicken","onions"}),
				new Receipe( "Bruschetta With Roasted Garlic and Cherry Tomatoes","http://img.recipepuppy.com/ 199304.jpg", "http://www.recipezaar.com/Bruschetta-With- Roasted-Garlic-and-Cherry-Tomatoes-244281",  new String[] {"garlic","italian bread"}),
				new Receipe( "Garlic Vinegar","http://img.recipepuppy.com/ 647882.jpg", "http://www.recipezaar.com/Garlic- Vinegar-251602", new String[] {"garlic","vinegar"}));

		arryOfIngredients = "{\"ingredients\":[\"italian bread\",\"garlic\",\"onions\"]}"; 
	}

	@Test//List<Receipe> receipes;
	public void testCreateReceipe() throws Exception {

		when(receipeService.saveReciepe(receipes)).thenReturn(receipeMOList);

		List<ReceipeEntity> receipeMOReturnList = receipeService.saveReciepe(receipes);

		assertEquals(receipeMOList, receipeMOReturnList);
		verify(receipeService, times(1)).saveReciepe(receipes);
	}

	@Test
	public void testExistsReceipe() throws Exception {
		when(receipeService.existsByTitle("Crock Pot Caramelized Onions")).thenReturn(true);

		assertTrue(receipeService.existsByTitle("Crock Pot Caramelized Onions"));
	}

	@Test
	public void testGetAllReceipe() throws Exception {

		when(receipeService.getAllReciepies()).thenReturn(receipeMOList);

		List<ReceipeEntity> receipeMOReturnList = receipeService.getAllReciepies();
		assertEquals(receipeMOList, receipeMOReturnList);
		assertEquals(4, receipeMOReturnList.size());
		verify(receipeService, times(1)).getAllReciepies();
		assertEquals("Crock Pot Caramelized Onions", receipeService.getAllReciepies().get(0).getTitle());
	}

	@Test
	public void testGetReecipesOfIngredients() throws Exception {

		Ingredients ingredientsVO = new Ingredients();
		ingredientsVO.setIngredients(new String[] { "garlic","onions","italian bread" });

		when(receipeService.getReciepeByIngredients(ingredientsVO)).thenReturn(receipeMOList);

		List<ReceipeEntity> receipeMOReturnList = receipeService.getReciepeByIngredients(ingredientsVO);

		assertEquals(4, receipeMOReturnList.size());
		assertEquals(receipeMOList, receipeMOReturnList);
		verify(receipeService, times(1)).getReciepeByIngredients(ingredientsVO);
		assertEquals("Crock Pot Caramelized Onions",
				receipeService.getReciepeByIngredients(ingredientsVO).get(0).getTitle());

	}

}
