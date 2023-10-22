package com.example.robolectrictest

import android.app.Application
import android.content.ContentProviderOperation
import android.provider.ContactsContract
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {
    private lateinit var application: Application

    @Before
    fun setUp() {
        application = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testContentResolver() {

        val sampleAccountType = "vnd.sec.contact.sim"

        val batchOps = arrayListOf<ContentProviderOperation>()

        val rawContactOp = ContentProviderOperation
            .newInsert(ContactsContract.RawContacts.CONTENT_URI)
            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, sampleAccountType)
            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "sim")

        batchOps.add(rawContactOp.build())

        Assert.assertNotNull(application.contentResolver)

        val results = application.contentResolver.applyBatch(ContactsContract.AUTHORITY, batchOps)

        Assert.assertNotNull(results)
    }
}