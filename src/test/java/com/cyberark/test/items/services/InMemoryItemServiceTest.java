package com.cyberark.test.items.services;

import com.cyberark.items.entities.*;
import com.cyberark.items.services.ItemService;
import com.cyberark.test.TestApp;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestApp.class}, initializers = ConfigFileApplicationContextInitializer.class)
public class InMemoryItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    public void checkScotchBottlePrice()
    {
        Item item = itemService.addItem(new Item(ItemType.SCOTCH_BOTTLE, 10, 10));

        itemService.updateItems();
        Assertions.assertThat(item.getPrice()).isEqualTo(11);
    }

    @Test
    public void checkTShirtPrice()
    {
        Item item = itemService.addItem(new Item(ItemType.T_SHIRT, 10, 10));

        itemService.updateItems();
        Assertions.assertThat(item.getPrice()).isEqualTo(9);
    }

    @Test
    public void checkBeerPrice()
    {
        Item item = itemService.addItem(new Item(ItemType.BEER, 10, 10));

        itemService.updateItems();
        Assertions.assertThat(item.getPrice()).isEqualTo(9);
    }

    @Test
    public void checkBasketballPrice()
    {
        Item item = itemService.addItem(new Item(ItemType.BASKETBALL, 10, 10));

        itemService.updateItems();
        Assertions.assertThat(item.getPrice()).isEqualTo(10);
    }

    @Test
    public void checkNoBiggerThan50Price()
    {
        Item item = itemService.addItem(new Item(ItemType.SCOTCH_BOTTLE, 10, 50));

        itemService.updateItems();
        Assertions.assertThat(item.getPrice()).isEqualTo(50);
    }

    @Test
    public void checkNoLessThan0Price()
    {
        Item item = itemService.addItem(new Item(ItemType.BEER, 10, 0));

        itemService.updateItems();
        Assertions.assertThat(item.getPrice()).isEqualTo(0);
    }

    @Test
    public void checkDaysToExpirePassPriceDrop2TimeFaster()
    {
        Item item = itemService.addItem(new Item(ItemType.BEER, 0, 2));

        itemService.updateItems();
        Assertions.assertThat(item.getPrice()).isEqualTo(0);
    }

}