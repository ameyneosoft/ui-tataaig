package com.journaldev.expandablelistview

import NavigationMenuGroup
import com.example.tataaig.R

object ExpandableListDataPump {
    val data: List<NavigationMenuGroup>
        get() {

            val list = ArrayList<NavigationMenuGroup>()
            val knowledgeItem: MutableList<String> = ArrayList()
            knowledgeItem.add("Products")
            knowledgeItem.add("Engagement")
            knowledgeItem.add("Agency Connect")

            val servicingItem: MutableList<String> = ArrayList()
            servicingItem.add("Claims")
            servicingItem.add("Endorsement")

            val helpItem : MutableList<String> = ArrayList()
            helpItem.add("Contact RM")
            helpItem.add("FAQs")
            helpItem.add("Raise a concern")

            list.add(NavigationMenuGroup(R.drawable.ic_knowledge_center,"Knowledge Center",knowledgeItem,true))
            list.add(NavigationMenuGroup(R.drawable.ic_knowledge_center,"Servicing",servicingItem,true))
            list.add(NavigationMenuGroup(R.drawable.ic_knowledge_center,"Renewals",ArrayList()))
            list.add(NavigationMenuGroup(R.drawable.ic_knowledge_center,"Quick Quote",ArrayList()))
            list.add(NavigationMenuGroup(R.drawable.ic_business_report,"Business Report",ArrayList()))
            list.add(NavigationMenuGroup(R.drawable.ph_hand_coins,"Gaint Steps",ArrayList()))
            list.add(NavigationMenuGroup(R.drawable.ic_campaign,"Campaigns",ArrayList()))
            list.add(NavigationMenuGroup(R.drawable.ph_hand_coins,"Commisions",ArrayList()))
            list.add(NavigationMenuGroup(R.drawable.locators,"Locators",ArrayList()))
            list.add(NavigationMenuGroup(R.drawable.ic_help,"Help",helpItem,true))
            list.add(NavigationMenuGroup(R.drawable.logout,"Logout",ArrayList()))

            return list
        }
}