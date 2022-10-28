package com.example.umlandowallet.data.remote

import com.example.umlandowallet.Global
import org.bitcoindevkit.*
import org.ldk.structs.ChainMonitor
import org.ldk.structs.ChannelManager
import java.io.File

interface Access {
    suspend fun syncWallet(wallet: Wallet, logProgress: Progress): Unit

    suspend fun sync(): Unit

    suspend fun syncTransactionsUnconfirmed(
        relevantTxIds: Array<ByteArray>,
        channelManager: ChannelManager,
        chainMonitor: ChainMonitor
    ): Unit

    companion object {
        fun create(): Access {
            // Setup BDK Esplora client
            val esploraURL: String = "http://10.0.2.2:3002"
            val blockchainConfig =
                BlockchainConfig.Esplora(EsploraConfig(esploraURL, null, 5u, 20u, null))

            return AccessImpl(
                blockchain = Blockchain(blockchainConfig),
            )
        }
    }
}