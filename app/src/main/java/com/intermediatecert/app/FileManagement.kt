package com.intermediatecert.app

import android.content.Context
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.*

object FileManagement {
  lateinit var storageDir: File
    internal set
  const val sslCertPath = "intermediatecert/ssl"
  lateinit var sslStorage: File
    private set

  internal object StorageManager{
    internal fun initialize(appContext: Context) {
      storageDir = appContext.filesDir
    }
  }
  fun init(context: Context) {
    StorageManager.initialize(context)

  }

  internal fun storeIntermediateSSCertificate(certificate: String, certificateName: String) {



    if (sslStorage.exists()) {
      //try {
      val certFactory = CertificateFactory.getInstance("X.509")
      return FileInputStream("com/$sslCertPath").use {
        certFactory.generateCertificate(it) as X509Certificate
      }

      val outputFile = File(sslStorage, certificateName).also {
        if (!it.exists()) it.createNewFile()
      }
      val decodeValue = Base64.decodeBase64(certificate.toByteArray(Charsets.UTF_8))
      if (decodeValue == null) {
        throw NullPointerException("Decode value null")
      } else {
        outputFile.writeBytes(decodeValue)
        logInfo("Certificate Saved Status: true")
      }
//            } catch (ex: NullPointerException) {
//                ex.printStackTrace()
//                logInfo("Certificate Saved Status: false")
//            }
    } else {
      throw Exception("SSL folder not created.")
    }
  }

}