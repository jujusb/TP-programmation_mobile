package com.example.tp5_vocabulaire

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object VocabulaireContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<WordItem> = ArrayList()

    /**
     * A map of sample (dummy) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, WordItem> = HashMap()

    private var COUNT = 0

    init {
        //lecture(getResources() R.raw.voiture)
        /*
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(
                createDummyItem(
                    i
                )
            )
        }*/
    }

    private fun addItem(item: WordItem) {
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    private fun createDummyItem(position: Int): WordItem {
        return WordItem(
            position.toString(),
            "Item " + position,
            makeDetails(position)
        )
    }

    private fun makeDetails(position: Int): String {
        val builder = StringBuilder()
        builder.append("Details about Item: ").append(position)
        for (i in 0..position - 1) {
            builder.append("\nMore details information here.")
        }
        return builder.toString()
    }

    fun lecture(inputStream: InputStream) {

        var reader= BufferedReader(InputStreamReader(inputStream))
        var line=reader.readLine()
        while (line!=null) {
            val tokens = line.split(";");
            if (tokens.size>0){
                addItem(WordItem(""+COUNT,tokens[0],tokens[1]))
                COUNT++
            }
            line=reader.readLine()
        }
    }

    /**
     * A dummy item representing a piece of content.
     */
    data class WordItem(val id: String, val content: String, val details: String) {
        override fun toString(): String = content
    }
}
