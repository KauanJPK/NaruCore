package com.github.kauanjpk.narucore.nms

import com.github.kauanjpk.narucore.api.NMSAdapter
import com.github.kauanjpk.narucore.nms.legacy.NMSLegacy
import com.github.kauanjpk.narucore.nms.modern.NMSModern
import com.github.kauanjpk.narucore.util.VersionResolver

object NMSLoader {

    fun load(): NMSAdapter {

        return if (VersionResolver.isLegacy)
            NMSLegacy()
        else
            NMSModern()

    }

}