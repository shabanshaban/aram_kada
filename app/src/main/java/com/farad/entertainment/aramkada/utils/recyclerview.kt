package com.farad.entertainment.aramkada.utils

import androidx.recyclerview.widget.RecyclerView


fun RecyclerView.checkStateScrollL(isScrollingUp:(Boolean)->Unit ){

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                isScrollingUp(dy <= 0)



            }
        })

}
